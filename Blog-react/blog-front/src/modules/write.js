// 글쓰기 관련 상태 리덕스 관리

import { createAction, handleActions } from 'redux-actions';
import createRequestSaga, { createRequestActionType } from '../lib/createRequestSaga';
import * as postsAPI from '../lib/api/posts';
import { takeLatest } from 'redux-saga/effects';

const INITIALIZE = 'write/INITTALIZE';
const CHANGE_FIELD = 'write/CHANGE_FIELD';
const SET_ORIGINAL_POST = 'write/SET_ORIGINAL_POST';
const [WRITE_POST, WRITE_POST_SUCCESS, WRITE_POST_FAILURE] = createRequestActionType('write/WRITE_POST');
const [UPDATE_POST, UPDATE_POST_SUCCESS, UPDATE_POST_FAILURE] = createRequestActionType('write/UPDATE_POST');

export const initialize = createAction(INITIALIZE);
export const changeField = createAction(CHANGE_FIELD, ({ key, value}) => ({
    key,
    value
}));
export const writePost = createAction(WRITE_POST, ({ title, body, tags }) => ({
    title, 
    body, 
    tags
}));
export const setOriginalPost = createAction(SET_ORIGINAL_POST, post => post);
export const updatePost = createAction(UPDATE_POST, ({ id, title, body, tags }) => ({id, title, body, tags}))

// saga
const writePostSaga = createRequestSaga(WRITE_POST, postsAPI.writePost);
const updatePostSaga = createRequestSaga(UPDATE_POST, postsAPI.updatePost);
export function* writeSaga() {
    yield takeLatest(WRITE_POST, writePostSaga);
    yield takeLatest(UPDATE_POST, updatePostSaga);
};

const initalState = {
    title: '',
    body: '',
    tags: [],
    post: null,
    postError: null,
    originalPostId: null
};

const write = handleActions({
    [INITIALIZE]: state => initalState,
    [CHANGE_FIELD]: (state, {payload: { key, value }}) => ({
        ...state,
        [key]: value
    }),
    [WRITE_POST]: state => ({
        ...state,
        post: null,
        psotError: null
    }),
    [WRITE_POST_SUCCESS]: (state, { payload: post }) => ({
        ...state,
        post
    }),
    [WRITE_POST_FAILURE]: (state, { payload: postError }) => ({
        ...state,
        postError
    }),
    [SET_ORIGINAL_POST]: (state, {payload: post}) => ({
        ...state,
        title: post.title,
        body: post.body,
        tags: post.tags,
        originalPostId: post._id
    }),
    [UPDATE_POST_SUCCESS]: (state, { payload: post}) => ({
        ...state,
        post
    }),
    [UPDATE_POST_FAILURE]: (state, {payload: postError}) => ({
        ...state,
        postError
    })
}, initalState);

export default write;