package org.example.crawlingappserver.controller;

import org.example.crawlingappserver.dto.req.FavoritesWholeRequestDTO;
import org.example.crawlingappserver.dto.res.FavoriteWholeResponseDTO;
import org.example.crawlingappserver.service.FavoriteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class FavoritesController {

    private final FavoriteService favoriteService;

    public FavoritesController(FavoriteService favoritesService) {
        this.favoriteService = favoritesService;
    }

    @PostMapping("/api/favorites/info")
    public List<FavoriteWholeResponseDTO> getFavoritesInfo(@RequestBody List<FavoritesWholeRequestDTO> favorites) {
        return favoriteService.getFavoritesInfo(favorites);
    }
}