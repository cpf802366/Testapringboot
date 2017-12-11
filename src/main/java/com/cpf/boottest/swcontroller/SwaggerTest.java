package com.cpf.boottest.swcontroller;

import com.cpf.boottest.pojo.UserEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cpf on 2017/12/7.
 */
@RestController
@RequestMapping(value = "/swaggertest")
@Api(value="用户controller",tags={"用户操作接口"})
public class SwaggerTest {
    @ApiOperation(value="测试", notes="根据url的id来获取详细信息")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long",paramType = "path")
    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public String  getBook(@PathVariable Long id) {
        System.out.println("swagger come in ...");
        return "success";
    }
    @ApiOperation(value="测试user", notes="根据url的user来获取详细信息")
    @ApiImplicitParam(name = "userEntity", value = "user实体", required = true, dataType = "UserEntity",paramType = "path")
    @RequestMapping(value="/getUser", method= RequestMethod.GET)
    public String  getUser(UserEntity userEntity) {
        System.out.println("swagger come in ..."+userEntity.getUsername());
        return "success";
    }

}
