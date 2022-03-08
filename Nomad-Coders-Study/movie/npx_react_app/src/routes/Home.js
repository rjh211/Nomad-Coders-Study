import { useState, useEffect } from "react";
import Movie from "../components/Movie";
import styles from "./Home.module.css";

const movieAPI = "https://yts.mx/api/v2/list_movies.json?minimum_rating=8.5&sort_by=year"

const Home = () => {
    const [loading, setLoading] = useState(true);
    const [movies, setMovies] = useState([]);
  
    const getMovies = async()=>{
      const response = await fetch(`https://yts.mx/api/v2/list_movies.json?minimum_rating=8.5&sort_by=year`);
      const json = await response.json();
      setMovies(json.data.movies);
      setLoading(false);
    }
  
    useEffect(getMovies,[])
  
    return (
        <div>
          {loading ?  <h1>Loading...</h1> : 
          <div>
            {movies.map(movie => <Movie 
            key={movie.id}
            id={movie.id} //-> App.js 의 router로 이동하여 id변수가됨
            medium_cover_image={movie.medium_cover_image} 
            title={movie.title} 
            summary={movie.summary}/>)}
            
          </div>}
        </div>
      );//li를 사용하기위해선 unique key를 넣어줘야함
}

export default Home;