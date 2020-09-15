package cn.jiang.mode;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName(value = "T_USER")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends Model{

    private Long id;

    private String userName;

    private  String password;

    private  Integer age;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserDto extends BaseRowModel{
        @ExcelProperty(value = "ID",index = 0)
        private Long id;
        @ExcelProperty(value = "姓名",index = 1)
        private String userName;
        @ExcelProperty(value = "密码",index = 2)
        private  String password;
        @ExcelProperty(value = "年龄",index = 3)
        private  Integer age;
    }

}
