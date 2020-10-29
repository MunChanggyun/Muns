import Router from 'koa-router';
import * as authCtrl from './AuthCtrl/auth.ctrl';

const auth = new Router();

auth.post('/login', authCtrl.login);
auth.post('/register', authCtrl.register);
auth.get('/loginCheck', authCtrl.loginCheck);
auth.post('/logout', authCtrl.logout);

export default auth;