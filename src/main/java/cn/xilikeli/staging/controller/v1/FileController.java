package cn.xilikeli.staging.controller.v1;

import cn.xilikeli.staging.bo.FileBO;
import cn.xilikeli.staging.common.util.ResponseUtil;
import cn.xilikeli.staging.service.FileService;
import cn.xilikeli.staging.vo.UnifyResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

/**
 * <p>
 * 文件上传 API 接口控制器
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/9/28 - 20:25
 * @since JDK1.8
 */
@Validated
@RestController
@RequestMapping("/v1/file")
@AllArgsConstructor
@Api(value = "文件上传 API 接口", tags = {"文件上传的相关接口"})
public class FileController {

    private final FileService fileService;

    /**
     * 文件上传接口
     *
     * @param multipartHttpServletRequest 携带文件的 request
     * @return 文件信息
     */
    @PostMapping
    @ApiOperation(value = "文件上传接口", notes = "文件上传接口", httpMethod = "POST")
    public UnifyResponseVO<List<FileBO>> upload(MultipartHttpServletRequest multipartHttpServletRequest) {
        MultiValueMap<String, MultipartFile> fileMap = multipartHttpServletRequest.getMultiFileMap();
        List<FileBO> uploadFileList = this.fileService.upload(fileMap);
        return ResponseUtil.generateUnifyResponseVO(uploadFileList);
    }

}