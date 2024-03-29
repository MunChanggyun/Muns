// 포스트 페이지, 포스트 목록 페이지 에서 사용할 헤더
import React from 'react';
import styled from 'styled-components';
import Responsive from './Responsive';
import Button from './Button';
import { Link } from 'react-router-dom';

const HeaderBlock = styled.div`
    position: fixed;
    width: 100%;
    background: white;
    box-shadow: 0px 2px 4px rgb(0, 0, 0, 0.08);
`;

/**
 * Responsive 컴포넌틔의 속성에 스타일을 추가하여 새로운 컴포넌트 생성
 */
const Wrapper = styled(Responsive)`
    height: 4rem;
    display:flex;
    align-items: center;
    justify-content: space-between;
    .logo {
        font-size: 1.125rem;
        font-weight: 800;
        letter-spacing: 2px;
    }
    .right {
        display: flex;
        align-items: center;
    }
`;

// const Wrapper = styled.div`
//     height: 4rem;
//     display:flex;
//     align-items: center;
//     justify-content: space-between;
//     .logo {
//         font-size: 1.125rem;
//         font-weight: 800;
//         letter-spacing: 2px;
//     }
//     .right {
//         display: flex;
//         align-items: center;
//     }
// `;

const Spacer = styled.div`
    height: 4rem;
`;

const UserInfo = styled.div`
    font-weigth: 800;
    margin-right: 1rem;
`

const Header = ({ user, onLogout }) => {
    return (
        <>
            <HeaderBlock>
                <Wrapper>
                    <Link to="/" className="logo">
                        REACTERS
                    </Link>
                    {user ? (
                        <div className="right">
                            <UserInfo>{ user.username }</UserInfo>
                            <Button to="/" onClick={onLogout}>로그아웃</Button>
                        </div>
                    ) : (
                        <div className="right">
                            <Button to="/login">로그인</Button>
                        </div>        
                    )}
                    
                </Wrapper>
            </HeaderBlock>
            <Spacer />
        </>
    );
};

export default Header;