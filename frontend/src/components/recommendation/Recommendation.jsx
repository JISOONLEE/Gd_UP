import React from 'react';
import ReactWordcloud from 'react-wordcloud';

export default function Recommendation() {
  const words = [
    {
      text: 'told',
      value: 64,
    },
    {
      text: 'mistake',
      value: 11,
    },
    {
      text: 'thought',
      value: 16,
    },
    {
      text: 'bad',
      value: 17,
    },
  ];

  return(
    <div>
      <h1>
        Recommendation
      </h1>
      <ReactWordcloud words={words} />
    </div>
  );
}
