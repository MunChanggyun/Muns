import React from 'react';
import styled from 'styled-components';
import Button from '../common/Button';
import qs from 'qs';

const PaginationBlock = styled.div`
    width: 320px;
    margin: 0 auto;
    display: flex;
    justify-content: space-between;
    margin-bottom: 3rem;
`;

const PageNumber = styled.div`
`;

const bulidLink = ({ username, tag, page }) => {
    const query = qs.stringify({ tag, page });
    return username ? `/@${username}?${query}` : `/?${query}`;
}

const Pagination = ({ page, lastPage, username, tag }) => {
    console.log(page, (page === 1));

    return (
        <PaginationBlock>
            <Button
                disabled={page === 1}
                to={page === 1 ? undefined : bulidLink({username, tag, page: page -1})}>
                이전
            </Button>
            <PageNumber>{page}</PageNumber>
            <Button
                disabled={page === lastPage}
                to={page === lastPage ? undefined : bulidLink({username, tag, page: page + 1})}>
                다음
            </Button>
        </PaginationBlock>
    );
};

export default Pagination;