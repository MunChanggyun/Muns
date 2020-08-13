// redux-saga를 통해 API 호출을 쉽게 하도록 설정
import { call, put } from 'redux-saga/effects';
import { startLoading, finishLoading } from '../modules/loading';

export const createRequestActionType = type => {
    const SUCCESS = `${type}_SUCCESS`;
    const FAILURE = `${type}_FAILURE`;

    return [type, SUCCESS, FAILURE];
}

export default function createRequestSaga(type, request) {
    const SUCCESS = `${type}_SUCCESS`;
    const FAILURE = `${type}_FAILURE`;

    return function*(action) {
        yield put(startLoading(type));

        try {
            const response =  yield call(request, action.payload);

            yield put({
                type: SUCCESS,
                payload: response.data,
                meta: response  // HTTP 헤더 및 상태 코드를 쉽게 조회하기 위해 추가
            });
        } catch (e) {
            yield put({
                type: FAILURE,
                payload: e,
                error: true
            });
        }

        yield put(finishLoading(type));
    }
}