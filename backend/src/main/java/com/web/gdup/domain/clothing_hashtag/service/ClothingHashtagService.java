package com.web.gdup.domain.clothing_hashtag.service;

import java.util.List;
import java.util.Set;

public interface ClothingHashtagService {
    public void insertHashtags(int clothing_id, Set<String> hashtags);
    List<String> getHashtags(int clothing_id);
}
