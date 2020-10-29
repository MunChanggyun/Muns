import { createAction, handleActions } from 'redux-actions';
import produce from 'immer';    // 불변성 관리를 위한 라이브러리
import createRequestSaga, { createRequestActionType } from '../lib/createRequestSaga'
import { takeLatest } from 'redux-saga/effects';
import * as authAPI from '../api/auth';

const INITIALZE_FORM = 'auth/INITIALZE_FORM';
const CHANGE_FORM = 'auth/CHANGE_FORM';
const [LOGIN, LOGIN_SUCCESS, LOGIN_FAILURE] = createRequestActionType('auth/LOGIN');    // 로그인

// input 초기화
export const initialze = createAction(INITIALZE_FORM, form => form);
// input change
export const changeForm = createAction(CHANGE_FORM, ({form, key, value}) => ({
    form,
    key,
    value
}));

// 로그인
export const login = createAction(LOGIN, ({ userId, password }) => ({
    userId,
    password
}));

// 사가 생성
const loginSaga = createRequestSaga(LOGIN, authAPI.login);

export function* authSaga() {
    yield takeLatest(LOGIN, loginSaga);
}

const initState = {
    register: {
        userId: '',
        password: '',
        passwordCheck: '',
    },
    login: {
        userId: '',
        password: ''
    },
    auth: null,
    authError: null
};

const auth = handleActions(
    {
        [INITIALZE_FORM]: (state, { payload : form}) => ({
            ...state,
            [form]: initState[form],
            auth: null
        }),
        [CHANGE_FORM]: (state, { payload : { form, key, value}}) => 
            produce(state, draft => {
                draft[form][key] = value;
            }),
        [LOGIN_SUCCESS]: (state, {payload: auth}) => ({
            ...state,
            authError: null,
            auth
        }),
        [LOGIN_FAILURE]: (state, { payload: error }) => ({
            ...state,
            authError: error
        })
            
    },
    initState
);

export default auth;