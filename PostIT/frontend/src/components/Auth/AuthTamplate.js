import React from 'react'
import styled from 'styled-components';
import palette from '../../lib/Palette';

const AuthTampleateBlock = styled.div`
    background-color: ${palette.main[1]};
    min-height: 100vh;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    font-size: calc(10px + 2vmin);
    color: white;
`;

const AuthTampleateBottomBlock = styled.div` 
    position: fixed;
    top: 50vh;
    height: 50vh;
    width: 100%;
    background: ${palette.main[3]};
    border-radius: 2px;
`;

 // 내부 영역
 const WhiteBox = styled.div`
    .logo-area {
        display: block;
        padding-bottom: 2rem;
        text-align: center;
        font-weight: bold;
        letter-spacing: 2px;
    }
    z-index: 9999;
    box-shadow: 0 0 8px rgba(0, 0, 0, 0.025);
    padding: 2rem;
    width: 360px;
    border-radius: 2px;
    
 `

// position: absolute;
//     left:0;
//     top:0;
//     bottom:0;
//     right:0;
//     background: ${palette.cyan[2]};
//     display: flex;
//     flex-direction: column;
//     justify-content: center;
//     align-items: center;


const AuthTampleate = ({ children }) => {
    return (
        <>
            <AuthTampleateBlock >
                <WhiteBox>
                    {children}
                </WhiteBox>
            </AuthTampleateBlock>
            <AuthTampleateBottomBlock />
            
        </>
    );
};

export default AuthTampleate;