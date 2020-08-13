import { createAction, handleActions} from 'redux-actions';
import produce from 'immer';
import { takeLatest } from 'redux-saga/effects';
import createRequestSaga, { createRequestActionType } from '../lib/createRequestSaga';
import * as authAPI from '../lib/api/auth';

const CHANGE_FIELD = 'auth/CHANGE_FIELD';
const INITIALIZE_FORM = 'auth/INITIALIZE_FORM';

// api 사용
const [REGISTER, REGISTER_SUCCESS, REGISTER_FAILURE] = createRequestActionType('auth/REGISTER');
const [LOGIN, LOGIN_SUCCESS, LOGIN_FAILURE] = createRequestActionType('auth/LOGIN');

export const changeField = createAction(
    CHANGE_FIELD,
    ({ form, key, value }) => ({
        form,   // register, login
        key,    // username, password, passwordConfirm
        value,  // 실제 바꾸려는 값
    }),
);

export const initializeForm = createAction(INITIALIZE_FORM, form => form);   // register
export const register = createAction(REGISTER, ({ username, password }) => ({
    username, 
    password,
}));
export const login = createAction(LOGIN, ({ username, password }) => ({
    username,
    password,
}));

// saga 생성
const registerSaga = createRequestSaga(REGISTER, authAPI.register);
const loginSaga = createRequestSaga(LOGIN, authAPI.login);

/**
 * 만약 마지막으로 발생된 리퀘스트의 응답만 얻고싶다면, takeLatest 헬퍼를 사용할 수 있습니다. (예: 항상 마지막 버전의 데이터만 보여주어야 할 때)
 * 
 * takeLatest 는 어느 순간에서도 단 하나의 fetchData 태스크만 실행되게 합니다. 
 * 마지막으로 시작된 태스크가 되겠죠. 만약 fetchData 태스크가 시작되었을때 이전 태스크가 실행중이라면, 
 * 이전 태스크는 자동적으로 취소될겁니다.
 * 
 * 참고 : https://mskims.github.io/redux-saga-in-korean/basics/UsingSagaHelpers.html
 */
export function* authSaga() {
    yield takeLatest(REGISTER, registerSaga);
    yield takeLatest(LOGIN, loginSaga);
}

const initalState = {
    register: {
        username: '',
        password: '',
        passwordConfirm: '',
    },
    login: {
        username: '',
        password: '',
    },
    auth: null,
    authError: null
};

const auth = handleActions(
    {
        [CHANGE_FIELD]: (state, {payload: {form, key, value} }) => 
            produce(state, draft => {
                draft[form][key] = value;   // 예시) state.register.username 을 변경  immer 를 사용하여 불변성 관리를 편하기 관리한다.
            }),
        [INITIALIZE_FORM]: (state, {payload: form}) => ({
            ...state,
            [form]: initalState[form],
            authError: null
        }),
        [REGISTER_SUCCESS]: (state, { payload: auth }) => ({
            ...state,
            authError: null,
            auth
        }),
        [REGISTER_FAILURE]: (state, { payload: error }) => ({
            ...state,
            authError: error
        }),
        [LOGIN_SUCCESS]: (state, { payload: auth }) => ({
            ...state,
            authError: null,
            auth
        }),
        [LOGIN_FAILURE]: (state, { payload: error }) => ({
            ...state,
            authError: error
        })      
    },
    initalState
);

export default auth;