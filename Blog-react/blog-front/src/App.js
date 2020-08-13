import React from 'react';
import LoginPage from './pages/LoginPage';
import PostListPage from './pages/PostListPage';
import PostPage from './pages/PostPage';
import RegisterPage from './pages/RegisterPage';
import WritePage from './pages/WritePage';
import { Route } from 'react-router-dom';

function App() {
  return (
    <>
      {/* path에 배열로 사용하면  /@:username, / 둘다 혀옹*/}
      <Route component={PostListPage} path={['/@:username', '/']} exact />  
      <Route component={LoginPage} path="/login" />
      <Route component={PostPage} path="/@:username/:postId" />
      <Route component={RegisterPage} path="/register" />
      <Route component={WritePage} path="/write" />
    </>
  );
}

export default App;
