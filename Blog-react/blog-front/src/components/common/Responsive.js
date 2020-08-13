// 로그인 후에 새로고침을 해도 로그인이 유지되도록

import React from 'react';
import styled from 'styled-components';

const ResponsiveBlock = styled.div`
    padding-left: 1rem;
    padding-right: 1rem;
    width: 1024px;
    margin: 0 auth; // 중앙정렬

    @media (max-width: 1024px) {
        width: 768px;
    }
    @media (max-width: 768px) { 
        width: 100%;
    }
}
`;

const Responsive = ({ children, ...rest}) => {
    return(
        <ResponsiveBlock {...rest}>{children}</ResponsiveBlock>
    );
};

export default Responsive;