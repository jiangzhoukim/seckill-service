import cn.jiang.controller.UserController;
import cn.jiang.mode.User;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: rockemq-redisson
 * @description: 测试
 * @author: Mr.Jiang
 * @create: 2020-09-14 17:13
 */
public class EasyexceldemoApplicationTests {
    //注入controller类用来调用返回list集合的方法
    @Autowired
    private UserController userController;


    @Test
    public void contextLoads(){
        // 文件输出位置
        OutputStream out = null;
        try {
            out = new FileOutputStream("E:\\test.xlsx");
            ExcelWriter writer = EasyExcelFactory.getWriter(out);
            // 写仅有一个 Sheet 的 Excel 文件, 此场景较为通用
            Sheet sheet1 = new Sheet(1, 0, User.UserDto.class);

            // 第一个 sheet 名称
            sheet1.setSheetName("第一个sheet");

            // 写数据到 Writer 上下文中
            // 入参1: 数据库查询的数据list集合
            // 入参2: 要写入的目标 sheet
            List<User.UserDto> userDtos = new ArrayList<>();
            for (int i = 0;i<=100;i++){
                User.UserDto userDto = new User.UserDto(1L,"name"+i,"qweeasd"+i,i);
                userDtos.add(userDto);
            }
            writer.write(userDtos, sheet1);

            // 将上下文中的最终 outputStream 写入到指定文件中
            writer.finish();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                // 关闭流
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
