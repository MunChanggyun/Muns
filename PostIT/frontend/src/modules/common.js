import { createAction, handleActions } from 'redux-actions';

const CHANGE_FORM = 'auth/CHANGE_FORM';

// input change
export const changeForm = createAction(CHANGE_FORM, ({ posts}) => ({
    posts
}));

const initState = {
    posts: []
};

const common = handleActions(
    {
        [CHANGE_FORM]: (state, { payload : posts}) => 
        ({
            ...state,
            posts
        })    
            
    },
    initState
);

export default common;