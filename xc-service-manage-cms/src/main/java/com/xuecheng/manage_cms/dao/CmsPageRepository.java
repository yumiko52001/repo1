package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsConfig;
import com.xuecheng.framework.domain.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;


public interface CmsPageRepository extends MongoRepository<CmsPage,String> {
    CmsPage findByPageName(String pagename);

    CmsPage findByPageNameAndSiteIdAndPageWebPath(String PageName,String SiteId,String PageWebPath);




}
