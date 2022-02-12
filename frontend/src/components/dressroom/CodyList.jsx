import React from 'react';

import { css } from "@emotion/react";

export default function CodyList({ ScrolltoTop, cards }) {
  return (
    <div>
      <h2>Cody</h2>
      <div css={container}>
        {cards.map((card, index) => {
          return (
            <img key={index} src={card} css={imgStyle} />
          );
        })}
        <button
          onClick={() => ScrolltoTop()}
          css={scrollBtn}
        >
          갤러리
        </button>
      </div>
    </div>
  );
}

const imgStyle = css`
  width: 90%;
  height: fit-content;
`;

const container = css`
  display: grid;
  grid-template-columns: repeat(5, 1fr);
  grid-gap: 10px;
`;

const scrollBtn = css`
  width: 150px;
  height: 30px;
  grid-column: 3;
  grid-row : 6;
  justify-self: center;
`;
