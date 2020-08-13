import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';
import { BrowserRouter } from 'react-router-dom';
import { Provider } from 'react-redux';
import { createStore, applyMiddleware } from 'redux';
import {composeWithDevTools} from 'redux-devtools-extension';
import createSagaMiddleware from 'redux-saga';
import rootReducer, { rootSaga } from './modules';
import { tempSetUser, check } from './modules/user';

const sagaMiddleware = createSagaMiddleware();
const store = createStore(rootReducer, composeWithDevTools(applyMiddleware(sagaMiddleware)));

/** 
 * 로그인 세션처리
 * 로그인 세션처리는 comoponentDidMount 나 useEffect 에서 처리해도 기능상의 문제는 없으나
 * 화면이 깜빡거리는 현상이 발생한다.
 * 해당 현상을 일으키지 않기 위해서는 index.js에서 세션 관련 작업을 진행하면 된다.
 */
function loadUser() {
  try {
    const user = localStorage.getItem('user');

    if (!user) return;  // 로그인 상태가 아님

    store.dispatch(tempSetUser(user))
    store.dispatch(check());
  } catch (e) {
    console.log("index.js ::::: localStorage is not working");
  }
}

sagaMiddleware.run(rootSaga);
loadUser(); // loadUser는 sagaMiddleware.run(rootSaga); 이 실행된 이후에 호출되어야 사가에서 제대도된 처리가 가능하다.

ReactDOM.render(
  <Provider store={store}>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </Provider>,
  document.getElementById('root')
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
