package cn.jiang.mode;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @program: seckill-service-master
 * @description: es测试类
 * @author: Mr.Jiang
 * @create: 2020-09-23 14:26
 */
@Data
@Accessors(chain = true)
@Document(indexName = "blog", type = "java")
public class BlogModel implements Serializable {
    private static  final long seralVersionUID = 632054814250372657L;

    private String id;

    private String title;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date time;
}
