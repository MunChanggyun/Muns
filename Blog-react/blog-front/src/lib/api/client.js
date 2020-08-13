// api 연동을 위한 axios 설정
import axios from 'axios';

const client = axios.create();

// // API 주소를 다른 곳으로 사용
// client.defaults.baseURL = 'https://external-api-server.com/';

// // 해더 설정
// client.defaults.headers.common['Autorization'] = 'Bearer A1b2c3d4'

// // 인터셉터 설정
// axios.interceptors.response.use(\
//     response => {
//         // 요청 성공시
//         return response;
//     }),
//     error => {
//         // 요청 실패시
//         return Promise.reject(error);
//     }

export default client;