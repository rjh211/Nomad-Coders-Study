import PropTypes from "prop-types";
import style from "./Button.module.css";

const Button = ({text}) => <button className={style.btn}>{text}</button>

Button.propTypes = {
    text: PropTypes.string.isRequired,
}

export default Button;