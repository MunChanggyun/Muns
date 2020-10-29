import React from 'react';
import { Route } from 'react-router-dom';
import LoginPage from './Pages/LoginPage';
import RegistePage from './Pages/RegistePage';
import postPage from './Pages/postPage';

function App() {
  return (
    <>
      <Route component={postPage} path="/post" />
      {/* <Route component={LoginPage} path={['/login', '/']} /> */}
      <Route component={LoginPage} path='/' exact/>
      <Route component={RegistePage} path="/register" />
      
    </>
  );
}

export default App;
