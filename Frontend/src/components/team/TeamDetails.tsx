import { useEffect, useMemo, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useParams } from "react-router-dom";
import { RootState } from "../../store/combine-store";
import { addTeam } from "../../store/team/team-store";
import { tTeamDetail } from "../../types/team";
import { getRequestWithID } from "../../utility/genericHTTPFunctions";
import RealTeamDetails from "./realteam/RealTeamDetails";
import UserTeamDetails from "./userteam/UserTeamDetails";

const TeamDetails: React.FC = () => {

    const [selectedTeam, setSelectedTeam] = useState<tTeamDetail>();
    const dispatch = useDispatch();
    const id = useParams().id;

    const { teams, token } = useSelector((state: RootState) => {
        return {
            teams: state.team.teams,
            token: state.authentication.token
        };
    });

    useEffect(() => {

        if (id) {
            const team = teams?.find(team => team.id === +id);
            if (team) {
                setSelectedTeam(team)
            } else if (token) {
                dispatch(getRequestWithID(+id, "/team/team", addTeam));
            }
        }

    }, [teams, id, dispatch, token]);

    const detailComponent = useMemo(() => {
        if (selectedTeam?.playerTeam) {
            return <UserTeamDetails team={selectedTeam} />
        } else if (selectedTeam?.playerTeam === false) {
            return <RealTeamDetails team={selectedTeam} />
        }
    }, [selectedTeam]);

    return <>
        <h3>{selectedTeam?.teamName}</h3>
        {detailComponent}
    </>;
};

export default TeamDetails;