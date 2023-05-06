package app.service.Impl;

import app.model.binding.CreateFlowerDto;
import app.model.binding.FlowerDto;
import app.model.binding.UpdateFlowerDto;
import app.model.entity.FlowerEntity;
import app.repository.FlowerRepository;
import app.service.FlowerService;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static app.constant.ConstantMessages.NO_PRODUCT_WITH_THIS_ID;

@Service
@Transactional
public class FlowerServiceImpl implements FlowerService {
    private final FlowerRepository flowerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public FlowerServiceImpl(FlowerRepository flowerRepository, ModelMapper modelMapper) {
        this.flowerRepository = flowerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public FlowerEntity createProduct(CreateFlowerDto createFlowerDto) throws Exception {
        FlowerEntity flowerEntity = fromCreatedFlowerEntity(createFlowerDto, modelMapper);
        return flowerRepository.save(flowerEntity);
    }

    @Override
    public long updateProduct(UpdateFlowerDto updateFlowerDto, long id) throws Exception {
        flowerRepository.findById(id).orElseThrow(() -> new NotFoundException(NO_PRODUCT_WITH_THIS_ID));
        FlowerEntity newFlowerEntity = fromUpdatedProductEntity(updateFlowerDto);
        return flowerRepository.save(newFlowerEntity).getId();
    }

    @Override
    public void deleteProduct(long id) throws Exception {
        FlowerEntity flowerEntity = flowerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(NO_PRODUCT_WITH_THIS_ID));

        flowerRepository.deleteById(flowerEntity.getId());
    }

    @Override
    public Page<FlowerDto> getProducts(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return flowerRepository.findAll(pageable).map(this::asProduct);
    }

    @Override
    public Optional<FlowerDto> getProductById(long id) throws Exception {
        FlowerEntity flowerEntity = flowerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(NO_PRODUCT_WITH_THIS_ID));

        return flowerRepository.findById(flowerEntity.getId()).map(this::asProduct);
    }

    private FlowerDto asProduct(FlowerEntity flowerEntity) {
        return modelMapper.map(flowerEntity, FlowerDto.class);
    }

    public FlowerEntity fromCreatedFlowerEntity(CreateFlowerDto createFlowerDto, ModelMapper modelMapper) {
        return modelMapper.map(createFlowerDto, FlowerEntity.class);
    }

    private FlowerEntity fromUpdatedProductEntity(UpdateFlowerDto updateFlowerDto) {
        return modelMapper.map(updateFlowerDto, FlowerEntity.class);
    }
}
