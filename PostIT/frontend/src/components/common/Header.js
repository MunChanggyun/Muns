import React from 'react';
import styled from 'styled-components';
import palette from '../../lib/Palette';
import Button from './Button';

const StyledDiv = styled.div`
    width: inherit;
    height: 3rem;
    background: ${palette.main[1]};
    display: flex;
    justify-content: space-between;
    padding: 5px 10px 5px 10px;
`;

const StyleLogoImg = styled.img`
    width: 7rem;
`;

const StyledButtonImg = styled.img`
    margin-left: 1rem;
    width: 3rem;
    heigth: 3rem;
`;

const WrapButton = styled.div`
    flex: 1;
    display: flex;
    justify-content: flex-end;
`;


const Header = ({ onLogout, onAddPost }) => {
    return (
        <StyledDiv >
            <StyleLogoImg src="/images/logo.png"/>
            <WrapButton>
                <Button onClick={onAddPost}>포스트 추가</Button>
                <span style={{width : "10px"}}></span>
                <Button onClick={onLogout} green >로그아웃</Button>
            </WrapButton>
            {/* <StyledButtonImg src="/images/plus.png"/> */}
        </StyledDiv>
    );
}

export default Header;