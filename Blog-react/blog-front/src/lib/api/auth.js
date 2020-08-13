// 회원인증에 필요한 API를 사용성을 위한 함수화 파일
import client from './client';

// 로그인
export const login = ({ username, password }) => 
    client.post('/api/auth/login', { username, password });

// 회원가입
export const register = ({ username, password }) => 
    client.post('/api/auth/register', { username, password });

// 로그인 상태확인
export const check = () => client.get('/api/auth/check');

// 로그아웃
export const logout = () => client.post('/api/auth/logout');