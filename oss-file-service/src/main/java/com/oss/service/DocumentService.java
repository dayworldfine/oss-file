package com.oss.service;

import com.oss.tool.BaseService;
import com.oss.pojo.dto.DocumentSignDto;
import com.oss.tool.ResponseResult;

/**
 * @ClassName：DocumentService
 * @Description: 文档实现类
 * @Author：13738700108
 * @Data 2020/10/19 21:30
 * @Version: v1.0
 **/
public interface DocumentService extends BaseService {
    /**
     * 获取文档预览地址相关信息
     * @param fileId
     * @return
     */
    ResponseResult getDocumentUrl(String fileId);

    /**
     * 刷新文档相关凭证
     * @param documentSignDto
     * @return
     */
    ResponseResult refreshSign(DocumentSignDto documentSignDto);
}
