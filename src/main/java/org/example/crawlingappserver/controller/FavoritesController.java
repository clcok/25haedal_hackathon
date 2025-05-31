package org.example.crawlingappserver.controller;

import lombok.RequiredArgsConstructor;
import org.example.crawlingappserver.dto.req.FavoritesWholeRequestDTO;
import org.example.crawlingappserver.dto.res.FavoriteWholeResponseDTO;
import org.example.crawlingappserver.service.FavoriteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FavoritesController {

    private final FavoriteService favoriteService;


    @PostMapping("/api/favorites/info")
    public List<FavoriteWholeResponseDTO> getFavoritesInfo(@RequestBody List<FavoritesWholeRequestDTO> favorites) {
        return favoriteService.getFavoritesInfo(favorites);
    }
}