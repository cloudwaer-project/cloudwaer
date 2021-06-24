package com.cloudwaer.channel.tags;

import com.cloudwaer.common.dto.ResponseDto;
import com.cloudwaer.common.dto.category.CategoryReqDto;
import com.cloudwaer.common.dto.tags.LabelReqDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @ClassName CategoryFeignClient
 * @Description TODO
 * @Author jiushiboy
 * @Date 2021/6/22 13:51
 * @Version 1.0
 **/
@Component
@FeignClient(value = "cloudwaer-tags",url = "localhost:9996/")
public interface LabelFeignClient {

    @PostMapping("/blogLabel/saveOrUpdateLabel")
    public ResponseDto saveOrUpdateLabel(@RequestBody List<LabelReqDto> labelReqDtos);
}
