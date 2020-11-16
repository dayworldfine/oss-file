import http from '@/util/http'

export const uploadFile = param => {
    return http.post({ url: '/file/uploadFile', data: param })
};

