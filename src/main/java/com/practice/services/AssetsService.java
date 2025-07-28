package com.practice.services;

import com.practice.dtos.AssetsDto;

import java.util.List;

public interface AssetsService {
    AssetsDto create(AssetsDto assetsDto);

    AssetsDto delete(String assetsId);

    AssetsDto getAsset(String assetsId);

    List<AssetsDto> getAllAssets();

}
