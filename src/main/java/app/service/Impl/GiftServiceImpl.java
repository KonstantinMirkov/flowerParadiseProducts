package app.service.Impl;

import app.model.binding.CreateGiftDto;
import app.model.binding.GiftDto;
import app.model.binding.UpdateGiftDto;
import app.model.entity.GiftEntity;
import app.repository.GiftRepository;
import app.service.GiftService;
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
public class GiftServiceImpl implements GiftService {
    private final GiftRepository giftRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public GiftServiceImpl(GiftRepository giftRepository, ModelMapper modelMapper) {
        this.giftRepository = giftRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public GiftEntity createProduct(CreateGiftDto createGiftDto) throws Exception {
        GiftEntity giftEntity = fromCreatedProductEntity(createGiftDto, modelMapper);
        return giftRepository.save(giftEntity);
    }

    @Override
    public long updateProduct(UpdateGiftDto updateGiftDto, long id) throws Exception {
        giftRepository.findById(id).orElseThrow(() -> new NotFoundException(NO_PRODUCT_WITH_THIS_ID));
        GiftEntity newGiftEntity = fromUpdatedProductEntity(updateGiftDto);
        return giftRepository.save(newGiftEntity).getId();
    }

    @Override
    public void deleteProduct(long id) throws Exception {
        GiftEntity giftEntity = giftRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(NO_PRODUCT_WITH_THIS_ID));

        giftRepository.deleteById(giftEntity.getId());
    }

    @Override
    public Page<GiftDto> getProducts(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return giftRepository.findAll(pageable).map(this::asProduct);
    }

    @Override
    public Optional<GiftDto> getProductById(long id) throws Exception {
        GiftEntity giftEntity = giftRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(NO_PRODUCT_WITH_THIS_ID));

        return giftRepository.findById(giftEntity.getId()).map(this::asProduct);
    }

    private GiftDto asProduct(GiftEntity giftEntity) {
        return modelMapper.map(giftEntity, GiftDto.class);
    }

    private GiftEntity fromCreatedProductEntity(CreateGiftDto createGiftDto, ModelMapper modelMapper) {
        return modelMapper.map(createGiftDto, GiftEntity.class);
    }

    private GiftEntity fromUpdatedProductEntity(UpdateGiftDto updateGiftDto) {
        return modelMapper.map(updateGiftDto, GiftEntity.class);
    }
}
