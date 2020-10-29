import api from './api'

export const list = () => 
    api.post('/postit/api/post/list');

export const update = ({ _id, title, content, top, left}) => 
    api.post(`/postit/api/post/update/${_id}`, { title, content, top, left});

export const insert = ({top, left, color}) => 
    api.post(`/postit/api/post/insert`, ({top, left, color}));

export const remove = ({_id}) => 
    api.post(`/postit/api/post/remove/${_id}`);

export const position = ({id, top, left}) =>
    api.post(`/postit/api/post/position/${id}`, {top, left});

export const changeColor = ({ _id, color }) => 
    api.post(`/postit/api/post/color/${_id}`, ({color}));

export default api;