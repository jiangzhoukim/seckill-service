package cn.jiang.controller;

import cn.jiang.mode.BlogModel;
import cn.jiang.properties.BlogModelRepositry;
import cn.jiang.result.ErrorInfo;
import cn.jiang.result.Result;
import com.alibaba.excel.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: seckill-service-master
 * @description: BlogController
 * @author: Mr.Jiang
 * @create: 2020-09-23 14:36
 */
@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogModelRepositry blogModelRepositry;

    @PostMapping("/add")
    public Result add(@RequestBody BlogModel blogModel){
        BlogModel save = blogModelRepositry.save(blogModel);
        return  Result.success(save);
    }

    @GetMapping("/get/{id}")
    public Result getById(@PathVariable String id) {
        BlogModel one = blogModelRepositry.findOne(id);
        return Result.success(one);
    }

    @GetMapping("/getAll")
    public Result getAll() {
        Iterable<BlogModel> all = blogModelRepositry.findAll();
        List<BlogModel> list = new ArrayList<>();
        all.forEach(list::add);
        return Result.success(list);
    }

    @PostMapping("/update")
    public Result updateById(@RequestBody BlogModel blogModel){
        if(StringUtils.isEmpty(blogModel.getId())){
            ErrorInfo errorInfo = new ErrorInfo();
            errorInfo.setMessage("错误");
            return Result.error(errorInfo);
        }
        BlogModel save = blogModelRepositry.save(blogModel);
        return  Result.success(save);
    }

    @GetMapping("/delete/{id}")
    public Result deleteById(@PathVariable String id) {
        blogModelRepositry.delete(id);
        return Result.success();
    }

    @DeleteMapping("/deleteAll")
    public Result deleteAll(){
        blogModelRepositry.deleteAll();
        return Result.success();
    }

    @GetMapping("/rep/search/{title}")
    public Result repSearchTitle(@PathVariable String title){
        return Result.success(blogModelRepositry.findByTitleLike(title));
    }
}
