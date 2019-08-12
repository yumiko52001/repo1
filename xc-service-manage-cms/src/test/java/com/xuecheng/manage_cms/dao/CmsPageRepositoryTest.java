package com.xuecheng.manage_cms.dao;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsPageParam;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import sun.nio.ch.IOUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

import static org.junit.Assert.*;


@SpringBootTest
@RunWith(SpringRunner.class)
public class CmsPageRepositoryTest {


    @Autowired
    CmsPageRepository cmsPageRepository;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    GridFsTemplate gridFsTemplate;
    @Autowired
    GridFSBucket gridFSBucket;

    @Test
    public void testFingAll() {
        /*int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        Page<CmsPage> all = cmsPageRepository.findAll(pageable);
        System.out.println(all);*/
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();


    }

    @Test
    public void testInsert() {
        CmsPage cmsPage = new CmsPage();
        cmsPage.setSiteId("s01");
        cmsPage.setTemplateId("t01");
        cmsPage.setPageName("测试页面");
        cmsPage.setPageCreateTime(new Date());
        List<CmsPageParam> cmsPageParams = new ArrayList<>();
        CmsPageParam cmsPageParam = new CmsPageParam();
        cmsPageParam.setPageParamName("param1");
        cmsPageParam.setPageParamValue("value1");
        cmsPageParams.add(cmsPageParam);
        cmsPage.setPageParams(cmsPageParams);
        cmsPageRepository.save(cmsPage);
        System.out.println(cmsPage);
    }

    @Test
    public void testUpdate(){
        Optional<CmsPage> op = cmsPageRepository.findById("5d3fd67090a2aa2a48aa29da");
        if(op.isPresent()){
            CmsPage cmsPage = op.get();
            cmsPage.setPageName("fuck");
            cmsPageRepository.save(cmsPage);
        }


    }
    @Test
    public void testFindBYExample(){
        int page = 1;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        CmsPage cmsPage = new CmsPage();
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
        Example<CmsPage> example = Example.of(cmsPage,exampleMatcher);

        Page<CmsPage> all = cmsPageRepository.findAll(example,pageable);
        List<CmsPage> content = all.getContent();
        System.out.println(content);


    }
    @Test
    public void testfind(){
        CmsPage page = cmsPageRepository.findByPageNameAndSiteIdAndPageWebPath("4028e58161bd3b380161bd3bcd2f0000.html", "5a751fab6abb5044e0d19ea1"
                , "/course/");

        System.out.println(page);


    }
    @Test
    public void testRestTemplate(){
        ResponseEntity<Map> forEntity = restTemplate.getForEntity("http://localhost:31001/cms/config/getModel/5a791725dd573c3574ee333f", Map.class);
        System.out.println(forEntity);


    }

    @Test
    public void testGridFs() throws Exception{
        File file = new File("H:\\xcED01\\xc-ui-pc-static-portal\\include\\index_banner.html");
        FileInputStream input = new FileInputStream(file);
        ObjectId objectId = gridFsTemplate.store(input, "轮播测试文件01");
        String id= objectId.toString();
        System.out.println(id);


    }

    @Test
    public void queryFile()throws Exception{
        String fileId= "5d4bf80190a2aa1cf449289c";
        Query id = Query.query(Criteria.where("_id").is(fileId));
        GridFSFile one = gridFsTemplate.findOne(id);
        ObjectId objectId = one.getObjectId();
        System.out.println(objectId);
        GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(one.getObjectId());
        GridFsResource gridFsResource = new GridFsResource(one,gridFSDownloadStream);
        String s = IOUtils.toString(gridFsResource.getInputStream(), "UTF-8");
        System.out.println(s);
    }



}