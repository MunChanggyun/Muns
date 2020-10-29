import { createAction, handleActions } from 'redux-actions';
import createRequestSaga, { createRequestActionType } from '../lib/createRequestSaga';
import { call, takeLatest } from 'redux-saga/effects';
import * as authAPI from '../api/auth';

const TEMP_SET_USER = 'user/TEMP_SET_USER';
const [CHECK, CHECK_SUCCESS, CHECK_FAILURE] = createRequestActionType('user/CHECK');
const LOGOUT = 'user/LOGOUT';

export const tempSetUser = createAction(TEMP_SET_USER, user => user);
export const check = createAction(CHECK);
export const logout = createAction(LOGOUT);

const checkSaga = createRequestSaga(CHECK, authAPI.loginCheck);
//const logoutSaga = createRequestSaga(LOGOUT, authAPI.logout);

export function* userSaga() {
    yield takeLatest(CHECK, checkSaga);
    yield takeLatest(CHECK_FAILURE, checkFailureSaga);
    yield takeLatest(LOGOUT, logoutSaga);
}
 
function* logoutSaga() {
    try{
        yield call(authAPI.logout);

        localStorage.removeItem('user');
        localStorage.removeItem('auth');
    } catch (e) {
        console.log("fail to logout");
    }
}

// 로그인 검증 실패시 정보 초기화
function checkFailureSaga() {
    try {
        localStorage.removeItem('user');
    } catch (e) {
        console.log('Fail to delete user info in localStorage');
    }
}

const initState = {
    user: null,
    checkError: null
}

const user = handleActions({
    [TEMP_SET_USER]: (state, {payload : user}) => ({
        ...state,
        user
    }),
    [CHECK_SUCCESS]: (state, { payload: user }) => ({
        ...state,
        checkError: null,
        user
    }),
    [CHECK_FAILURE]: (state, { payload : error }) => ({
        ...state,
        user: null,
        checkError: error
    }),
    [LOGOUT]: state => ({
        ...state,
        user:null
    })
}, initState);

export default user;