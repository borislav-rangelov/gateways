package com.example.demo.util.swagger;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE})
//@Retention(RetentionPolicy.RUNTIME)
//@ApiImplicitParams({
//        @ApiImplicitParam(name = "page", dataType = "int", paramType = "query", defaultValue = "0", value = "Results page you want to retrieve (0..N)"),
//        @ApiImplicitParam(name = "size", dataType = "int", paramType = "query", defaultValue = "20", value = "Number of records per page."),
//        @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). "
//                + "Default sort order is ascending. " + "Multiple sort criteria are supported.")})
@Getter
@Setter
public class SwaggerPageable {
    @ApiModelProperty(value = "Results page you want to retrieve (0..N)", example = "0")
    private Integer page;
    @ApiModelProperty(value = "Number of records per page.", example = "20")
    private Integer size;
    @ApiModelProperty("Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.")
    private String sort;
}
