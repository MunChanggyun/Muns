import { createAction, handleActions} from 'redux-actions';
import { takeLatest, call } from 'redux-saga/effects';
import * as authAPI from '../lib/api/auth';
import createRequestSaga, { createRequestActionType } from '../lib/createRequestSaga'; 

const TEMP_SET_USER = 'user/TEMP_SET_USER'; // 새로고침 이후 임시 로그인 처리
const [CHECK, CHECK_SUCCESS, CHECK_FAILURE] = createRequestActionType("user/CHECK");    // 회원정보 확인
const LOGOUT = 'auth/LOGOUT';

export const tempSetUser = createAction(TEMP_SET_USER, user => user);
export const check = createAction(CHECK);
export const logout = createAction(LOGOUT);

const checkSaga = createRequestSaga(CHECK, authAPI.check);

export function* userSaga() {
    yield takeLatest(CHECK, checkSaga);
    yield takeLatest(CHECK_FAILURE, checkFailureSaga);
    yield takeLatest(LOGOUT, logoutSaga)
};

const initialState = {
    user: null,
    checkError: null
};

// 로그아웃
function* logoutSaga() {
    try {
        yield call(authAPI.logout);

        localStorage.removeItem('user');
    } catch (e) {
        console.log('Fail to delete user info in localStorage', e);
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

export default handleActions({
    [TEMP_SET_USER]: (state, { payload: user }) => ({
        ...state,
        user,
    }),
    [CHECK_SUCCESS]: (state, { payload: user }) => ({
        ...state,
        user,
        checkerror: null
    }),
    [CHECK_FAILURE]: (state, { payload: error }) => ({
        ...state,
        user: null,
        checkerror: error
    }),
    [LOGOUT]: state => ({
        ...state, 
        user: null
    })
}, initialState);

