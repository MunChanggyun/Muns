import api from './api';

export const login = ({ userId, password }) => 
    api.post('/postit/api/auth/login', { userId, password });


export const register = ({ userId, password }) => 
    api.post('/postit/api/auth/register', { userId, password });


export const loginCheck = () => 
    api.get('/postit/api/auth/loginCheck');


export const logout = () => 
    api.post('/postit/api/auth/logout');


export default api;