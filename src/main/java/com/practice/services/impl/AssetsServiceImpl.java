package com.practice.services.impl;

import com.practice.dtos.AssetsDto;
import com.practice.entities.Assets;
import com.practice.exception.ResourceNotFoundException;
import com.practice.repositories.AssetsRepository;
import com.practice.services.AssetsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AssetsServiceImpl implements AssetsService {

    @Autowired
    private AssetsRepository assetsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AssetsDto create(AssetsDto assetsDto) {

        assetsDto.setAssetsId(UUID.randomUUID().toString());
        Assets assets = modelMapper.map(assetsDto, Assets.class);

        Assets savedAssets = assetsRepository.save(assets);
        AssetsDto map = modelMapper.map(savedAssets, AssetsDto.class);

        return map;
    }

    @Override
    public AssetsDto delete(String assetsId) {

        Assets assets = assetsRepository.findById(assetsId).orElseThrow(() -> new ResourceNotFoundException("Given assets not found"));

        assetsRepository.delete(assets);

        return modelMapper.map(assets, AssetsDto.class);
    }

    @Override
    public AssetsDto getAsset(String assetsId) {
        Assets assets = assetsRepository.findById(assetsId).orElseThrow(() -> new ResourceNotFoundException("Given assets not found"));

        return modelMapper.map(assets, AssetsDto.class);
    }

    @Override
    public List<AssetsDto> getAllAssets() {
        List<Assets> allAssets = assetsRepository.findAll();
        List<AssetsDto> collect = allAssets.stream().map(assets -> modelMapper.map(assets, AssetsDto.class)).collect(Collectors.toList());
        return collect;
    }
}
