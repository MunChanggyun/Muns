import React from 'react';
import styled from 'styled-components';
import palette from '../../lib/Palette';
import Button from '../common/Button';
import { Link } from 'react-router-dom';

const StyledInput = styled.input`
    background: white;
    border: 3px solid ${palette.main[1]};
    border-radius: 5px 5px 5px 5px;
    width: 100%;
    height: 30px;
    margin-bottom: 10px; 
`;

const StyledLink = styled(Link)`
  color: ${palette.main[1]};
  font-size: 1rem;
  text-decoration: none;
`;

const WrapLink = styled.div`
    text-align: end;
`;

const ErrorDiv = styled.div`
    color: #1a3e2d;
    text-align: right;
    font-size: 1rem;
    font-weight: bold;
`;

const onClick = () => {

}

const AuthForm = ({ type, form, onChange, onSubmit, error }) => {
    return (
        <>
            <form onSubmit={onSubmit}>
                <StyledInput 
                    name="userId"
                    onChange={onChange}
                    value={form.userId}
                    placeholder="아이디 "/>
                <StyledInput 
                    name="password"
                    onChange={onChange}
                    value={form.password}
                    placeholder="비밀번호 "/>
                {type === "register" && (
                    <StyledInput placeholder="비밀번호 확인"/>
                )}
                {error && <ErrorDiv>{error}</ErrorDiv>}
                <div style={{float: "right"}}>
                    <WrapLink>
                        {type === "register" ? (
                            <StyledLink to="/login">로그인</StyledLink>
                        ) : (
                            <StyledLink to="/register">회원가입</StyledLink>
                        )}
                    </WrapLink>
                    &nbsp;
                    <Button onClick={onClick}>로그인</Button>
                </div>
            </form>
            
        </>
    );
}

export default AuthForm;