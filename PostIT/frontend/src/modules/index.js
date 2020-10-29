import { combineReducers } from 'redux';
import auth, {authSaga} from './auth';
import user, {userSaga} from './user';
import posts, {PostSaga} from './posts';
import common from './common';
import loading from './loading';
import { all } from 'redux-saga/effects';

const rootReducer = combineReducers({
    auth,
    loading,
    user,
    posts,
    common
});

export function* rootSaga() {
    yield all([authSaga(), userSaga(), PostSaga()]);
}

export default rootReducer;
