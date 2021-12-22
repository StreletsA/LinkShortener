package com.StreletsA.linkshortener.repository;

import com.StreletsA.linkshortener.entity.LinkInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkInfoRepository extends CrudRepository<LinkInfo, String> {
	LinkInfo findByOriginalLink(String originalLink);
}
