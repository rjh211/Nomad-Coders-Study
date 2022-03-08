import PropTypes from "prop-types";
import {Link} from "react-router-dom";
import styles from "./Movie.module.css"

const Movie = ({medium_cover_image, title, summary, id}) => {
    return <div>
      <img src={medium_cover_image} alt={title}/>
      <h2>
        <Link to = {`movie/${id}`}>{title}</Link>
      </h2>
      <p>{summary}</p>
    </div>;
}

Movie.propTypes = {
  id : PropTypes.number.isRequired,
  medium_cover_image : PropTypes.string.isRequired,
  title : PropTypes.string.isRequired,
  summary : PropTypes.string.isRequired,
};

export default Movie;

