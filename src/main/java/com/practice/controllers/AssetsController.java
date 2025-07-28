package com.practice.controllers;

import com.practice.dtos.AssetsDto;
import com.practice.response.ApiResponse;
import com.practice.services.AssetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assets")
public class AssetsController {

    @Autowired
    private AssetsService assetsService;

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody AssetsDto assetsDto) {
        AssetsDto assetsDto1 = assetsService.create(assetsDto);
        ApiResponse assetsIsCreated = ApiResponse.builder().message("Assets is created").status(HttpStatus.CREATED.value()).data(assetsDto1).build();
        return new ResponseEntity<>(assetsIsCreated, HttpStatus.CREATED);
    }

    @DeleteMapping("/{assetsId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable String assetsId) {
        AssetsDto delete = assetsService.delete(assetsId);
        ApiResponse assetsIsDeleted = ApiResponse.builder().data(delete).status(HttpStatus.OK.value()).message("Assets is deleted").build();
        return new ResponseEntity<>(assetsIsDeleted, HttpStatus.OK);
    }

    @GetMapping("/{assetsId}")
    public ResponseEntity<ApiResponse> get(@PathVariable String assetsId) {
        AssetsDto asset = assetsService.getAsset(assetsId);
        ApiResponse assetsIsGetting = ApiResponse.builder().data(asset).status(HttpStatus.OK.value()).message("Assets is getting").build();
        return new ResponseEntity<>(assetsIsGetting, HttpStatus.OK);
    }
}
