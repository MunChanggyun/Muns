import React, { useRef, useState, useEffect } from 'react'
import styled, { css } from 'styled-components';
import { useSelector } from 'react-redux';
import { useDispatch } from 'react-redux';
import { movePost } from '../../modules/posts';
import Palette from '../../lib/Palette';

const StyledDiv = styled.div`
    position: absolute;
    
`;

const StyledIconWrap = styled.div`
    height: 55px;
`;

const StyledContentWrap = styled.div`
    padding: 10px;
`;

const StyledInput = styled.textarea`
    width: -webkit-fill-available;
    height: 150px;
`;

const StyledImage = styled.img`
    width: 15px;
    position: absolute;
    top: 24px;
    left: 278px;
    ${props=> 
        props.refresh && 
        css`
            top: 10px;
            left: 10px;
        `}
`;

const Post = ({ post, onBlur, onDelete, onChangeColor }) => {
    const dispatch = useDispatch();

    const {user} = useSelector(({user}) =>({
        user: user.user
    }));

    const [position, setPosition] = useState({
        top: post.top,
        left: post.left
    });
    const [inputChage, setInputChage] = useState({title: post.title, content: post.content});
    const isFirst = useRef(true);

    const onChange = e =>{
        e.preventDefault();
        setInputChage({
            ...inputChage,
            [e.target.name]: e.target.value
        });
    };

    const onMove = e => {
        if (e.target.className.indexOf("concrete") > -1) {
            return;
        }

        const wrap = document.getElementById("wrap");
        const wrapWidth = wrap.offsetWidth;
        const wrapHeight = wrap.offsetHeight; 
        
        const maxHeight = wrapHeight - 320;
        const maxWidth = wrapWidth - 330;

        let moveWidth = (Math.random() * wrapWidth);
        let moveHeight = (Math.random() * wrapHeight);

        if (moveHeight > maxHeight) {
            console.log("wrapHeight", wrapHeight, maxHeight);
            moveHeight = maxHeight
        };

        if (moveWidth > maxWidth) {
            console.log("wrapWidth", wrapWidth, maxWidth);
            moveWidth = maxWidth
        };

        setPosition({
            top: moveHeight,
            left: moveWidth
        });
    }

    const inputFocus = useRef(null);

    useEffect(() => {
        if (post.publishedDate === '' || post.publishedDate === null) {
            inputFocus.current.focus();
    
        }
    },[post.title]);

    useEffect(() => {
        if (isFirst.current) {
            isFirst.current = false;
            return;
        }
        const id = post._id;

        const { top, left } = position;

        dispatch(movePost({id, top, left}));

    },[position]); 

    return(
        <StyledDiv 
            style={position}
            onClick={onMove}
            >
            <StyledIconWrap>
                {post.user == null ? <></> : user._id === post.user._id ? (
                    <>
                        <img src={"/images/post_on_"  + post.color + ".png"} />
                        <StyledImage className="concrete" refresh={true} onClick={onChangeColor} src="/images/refresh.png" />
                        <StyledImage className="concrete" onClick={onDelete} src="/images/cross.png" />
                    </>
                ) : (
                    <img src={"/images/post_off_"  + post.color + ".png"} />
                )}
            </StyledIconWrap>
            <StyledContentWrap>
                <div>
                    <StyledInput
                        className="concrete"
                        name="content"
                        value={inputChage.content} 
                        onChange={onChange}
                        onBlur={onBlur}
                        ref={inputFocus} 
                        style={{"backgroundColor": Palette.postColorHex[post.color], "borderColor":  Palette.postColorHex[post.color]}}
                    ></StyledInput>
                </div>
            </StyledContentWrap>
        </StyledDiv>
    );
};

export default Post;