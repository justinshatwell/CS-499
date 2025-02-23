/*GET Homepage*/

const index = (req,res) => {
    res.render('index', {title: "Nema Event Control Panel"});
};

module.exports = {
    index
};