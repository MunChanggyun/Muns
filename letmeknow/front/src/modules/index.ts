import { combineReducers } from 'redux'
import auth, {authSaga} from './auth'
import loading from './loading'
import user, {userSaga} from './user'
import company, {companySaga} from './company'
import { all } from 'redux-saga/effects'

const rootReducer = combineReducers({
    auth, loading, company, user
})

export function* rootSaga() {
    yield all([authSaga(), userSaga(), companySaga()])
}

export default rootReducer

/**
 * 이 타입은 추후 컨테이너 컴포넌트를 만들게 될 때 스토어에서 관리하고 있는 상태를 조회하기 위해서 useSelector를 사용 할 때 필요
 */
export type RootState = ReturnType<typeof rootReducer>