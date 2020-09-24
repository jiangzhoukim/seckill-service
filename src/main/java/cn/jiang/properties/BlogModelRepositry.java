package cn.jiang.properties;


import cn.jiang.mode.BlogModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: seckill-service-master
 * @description: 测试
 * @author: Mr.Jiang
 * @create: 2020-09-23 14:21
 */
@Service
public interface BlogModelRepositry extends ElasticsearchRepository<BlogModel,String> {

    List<BlogModel> findByTitleLike(String title);
}
