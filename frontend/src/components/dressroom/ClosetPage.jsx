import React from 'react';
import { Link } from 'react-router-dom';
import ClothesItemListContainer from '../../containers/dressroom/ClothesItemListContainer';
import FilterContainer from '../../containers/dressroom/FilterContainer.jsx';
import AddClothesContainer from '../../containers/dressroom/AddClothesContainer.jsx';
import ClothesDetailContainer from '../../containers/dressroom/ClothesDetailContainer.jsx';

import { css } from '@emotion/react';

export default function ClosetPage({ onClickModal, filteredClothes }) {
  console.log(filteredClothes);

  return (
    <div>
      <div css={GridWrapper}>
        <FilterContainer />
        <div>
          <ClothesItemListContainer />
          <button
            onClick={onClickModal}
          >
            옷 추가
          </button>
        </div>
        <ClothesDetailContainer />
        <AddClothesContainer />
        <Link to='/dressroom'>
          <button>
            뒤로
          </button>
        </Link>
      </div>
    </div>
  );
}

const GridWrapper = css`
  width: 90rem;
  margin: 10rem auto;
	display: grid;
  grid-template-columns: 17rem 35rem 35rem;
  grid-row-gap: 10px;
  grid-auto-rows: minmax(100px, auto);
  grid-auto-columns: minmax(100px, auto);
`;