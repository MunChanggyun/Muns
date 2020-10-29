import React from 'react';
import styled, { css } from 'styled-components';
import palette from '../../lib/Palette';
import { withRouter, Link } from 'react-router-dom';

const ButtonStyle = css`
    border: none;
    border-radius: 4px;
    font-size: 1rem;
    font-weight: bold;
    padding: 0.25rem 1rem;
    color: white;
    outline: none;
    cursor: pointer;
    background: ${palette.cyan[8]};
    &:hover {
        background: ${palette.cyan[6]}
    }
    ${props=> 
        props.fullWidth && 
        css`
            padding-top: 0.75rem;
            padding-bottom: 0.75rem;
            width: 100%;
            font-size: 1.125rem;
        `}

    ${props => 
        props.green &&
        css`
            background: ${palette.green[9]};
            &:hover {
                background: ${palette.green[8]};
            }
        `}
    &:disabled {
        background: ${palette.cyan[3]};
        color: ${palette.cyan[5]};
        cursor: not-allowed;
    }
`;

const ButtonBlock = styled.button`
    ${ButtonStyle}
`;

const StyledLink = styled(Link)`
    ${ButtonStyle}
`;


const Button = props => {
    return (
        <>
            { props.to ? (
                <StyledLink {...props} green={props.green ? 1 : 0} />
            ) : (
                <ButtonBlock {...props} green={props.green ? 1 : 0} />
            )}
        </>
        
    );
}

export default withRouter(Button);
