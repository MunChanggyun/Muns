import { createAction, handleActions } from 'redux-actions';
import createRequestSaga, { createRequestActionType } from '../lib/createRequestSaga';
import * as postAPI from '../lib/api/posts';
import { takeLatest } from 'redux-saga/effects';

const [READ_POST, READ_POST_SUCCESS, READ_POST_FAILURE] = createRequestActionType('post/READ_POST');
const UNLOAD_POST = 'post/UNLOAD_POST'; // 페이지를 벗어날때 리덧스 상태의 데이터를 비우는 작업 
                                        // 만약 비우는 작업을 하지 않으면 특정 포스트를 읽을뒤 돌아가서 다른 포스트를 읽을때 깜빡이는 현상이 발생한다.

export const readPost = createAction(READ_POST, id => id);
export const unloadPost = createAction(UNLOAD_POST);


const readPostSaga = createRequestSaga(READ_POST, postAPI.readPost);

export function* postSaga() {
    yield takeLatest(READ_POST, readPostSaga);
}

const initialState = {
    post: null,
    error: null
};

const post = handleActions({
    [READ_POST_SUCCESS]: (state, {payload: post}) => ({
        ...state,
        post
    }),
    [READ_POST_FAILURE]: (state, {payload: error}) => ({
        ...state,
        error
    }),
    [UNLOAD_POST]: () => initialState
}, initialState);

export default post; 